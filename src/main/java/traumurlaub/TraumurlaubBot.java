package traumurlaub;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TraumurlaubBot extends TelegramLongPollingBot {

    private Map<String, Antwort> userAntworten = new HashMap<>();
    Frage frage = new Frage();

    @Override
    public void onUpdateReceived(Update update) {
        String chatId;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        } else {
            chatId = null;
        }

        if (chatId == null) return;

        userAntworten.putIfAbsent(chatId, new Antwort());
        Antwort antwort = userAntworten.get(chatId);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();

            if (userMessage.equals("/start")) {
                try {
                    sendMessage(chatId, "🐣🐣Hallo!🐣🐣");
                    sendMessage(chatId, "Willkommen bei TraumUrlaub! Lass uns für dich einen wunderschönen Urlaubsort finden...");
                    sendMessage(chatId, "Bereit?");
                    sendMessage(chatId, "3️⃣");
                    sendMessage(chatId, "2️⃣");
                    sendMessage(chatId, "1️⃣");
                    sendMessage(chatId, "Los geht’s! 🚀");


                    sendMessage(chatId, frage.getFrage1());
                    String basePath = "src/main/resources/images/";
                    execute(photo(chatId, basePath + "strand.png", "🏖️ Strandurlaub"));
                    execute(photo(chatId, basePath + "schnee.png", "⛷️ Schneeurlaub"));
                    execute(photo(chatId, basePath + "wandern.png", "🌲 Naturwanderung"));
                    execute(photo(chatId, basePath + "stadt.png", "🏙️ Städtetrip"));
                    execute(photo(chatId, basePath + "abenteuerlich.png", "🎆 Außergewöhnliches Erlebnis"));

                    sendErsteFrageButtons(chatId);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                return;
            }
        }

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            String callbackId = update.getCallbackQuery().getId();

            try {
                switch (callbackData) {
                    case "strand":
                    case "schnee":
                    case "wandern":
                    case "stadt":
                    case "abenteuerlich":
                        antwort.setKategorie(callbackData);
                        answerCallback(callbackId);
                        sendMessage(chatId, getKategorieAntwort(callbackData));
                        sendZweiteFrageButtons(chatId);
                        break;

                    case "ruhig":
                    case "lebendig":
                        antwort.setStimmung(callbackData);
                        answerCallback(callbackId);
                        sendMessage(chatId, getStimmungAntwort(callbackData));
                        sendDritteFrageButtons(chatId);
                        break;

                    case "günstig":
                    case "luxuriös":
                        antwort.setBudget(callbackData);
                        answerCallback(callbackId);
                        sendMessage(chatId, getBudgetAntwort(callbackData));

                        sendMessage(chatId, "Du sagst " + antwort.getKategorie() + ", " + antwort.getStimmung() + " und " + antwort.getBudget() + "...");
                        sendMessage(chatId, "Hmmm...");
                        sendGif(chatId, "src/main/resources/images/hmmm.gif");

                        Vorschlag vorschlaglist = new Vorschlag();
                        String key = antwort.getKategorie() + "|" + antwort.getStimmung() + "|" + antwort.getBudget();
                        System.out.println(key);
                        List<String> list = vorschlaglist.getVorschlaege(key);
                        int random = ThreadLocalRandom.current().nextInt(0, list.size());
                        String vorschlag = list.get(random);

                        sendGif(chatId, "src/main/resources/images/world.gif");

                        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
                        scheduler.schedule(() -> {
                            try {
                                sendMessage(chatId, "✨🌟💫 Mein Vorschlag für dich...💫🌟✨");
                                sendMessage(chatId, vorschlag);

                                String fileName = vorschlag
                                        .toLowerCase()
                                        .replaceAll("[^a-z]", "")
                                        + ".png";

                                String imagePath = "src/main/resources/images/" + fileName;
                                File file = new File(imagePath);

                                if (file.exists()) {
                                    execute(photo(chatId, imagePath, "📸 Schau mal, wie es dort aussieht!"));
                                } else {
                                    sendMessage(chatId, "Ich konnte kein Bild finden 😿");
                                    System.out.println("Bild nicht gefunden für: " + imagePath);
                                }
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }, 5, TimeUnit.SECONDS);

                        userAntworten.remove(chatId);
                        break;
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private String getKategorieAntwort(String kat) {
        return switch (kat) {
            case "strand" -> "🏖️ Du scheinst Strand und Sonne zu lieben!";
            case "schnee" -> "❄️ Ein Wintermärchen wartet auf dich!";
            case "wandern" -> "🌲 Natur und Bewegung, tolle Wahl!";
            case "stadt" -> "🏙️ Urbanes Entdecken steht dir gut!";
            case "abenteuerlich" -> "🎆 Bereit für etwas ganz Besonderes?!";
            default -> "";
        };
    }

    private String getStimmungAntwort(String stim) {
        return switch (stim) {
            case "ruhig" -> "🦋 Du magst es ruhig und entspannt.";
            case "lebendig" -> "🎉 Du liebst Action und Unterhaltung!";
            default -> "";
        };
    }

    private String getBudgetAntwort(String bud) {
        return switch (bud) {
            case "günstig" -> "💸 Zum Glück habe ich auch günstige Optionen!";
            case "luxuriös" -> "💎 Du möchtest dir etwas gönnen, sehr schön!";
            default -> "";
        };
    }

    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private SendPhoto photo(String chatId, String imagePath, String caption) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId);
        photo.setCaption(caption);
        photo.setPhoto(new InputFile(new File(imagePath)));
        return photo;
    }

    private InlineKeyboardButton button(String text, String data) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(data);
        return button;
    }

    private void sendGif(String chatId, String gifPath) {
        SendAnimation animation = new SendAnimation();
        animation.setChatId(chatId);
        animation.setAnimation(new InputFile(new File(gifPath)));

        try {
            execute(animation);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendErsteFrageButtons(String chatId) throws TelegramApiException {
        SendMessage message = new SendMessage(chatId, "Worauf hast du jetzt richtig Lust?");
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(List.of(button("🏖️ Strand", "strand")));
        rows.add(List.of(button("❄️ Schnee", "schnee")));
        rows.add(List.of(button("🌲 Wandern", "wandern")));
        rows.add(List.of(button("🏙️ Stadt", "stadt")));
        rows.add(List.of(button("🎆 Abenteuerlich", "abenteuerlich")));
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);
        execute(message);
    }

    private void sendZweiteFrageButtons(String chatId) throws TelegramApiException {
        SendMessage message = new SendMessage(chatId, frage.getFrage2());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(List.of(button("🦋 Ruhig", "ruhig")));
        rows.add(List.of(button("🎉 Lebendig", "lebendig")));
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);
        execute(message);
    }

    private void sendDritteFrageButtons(String chatId) throws TelegramApiException {
        SendMessage message = new SendMessage(chatId, frage.getFrage3());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        rows.add(List.of(button("💸 Günstig", "günstig")));
        rows.add(List.of(button("💎 Luxuriös", "luxuriös")));
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);
        execute(message);
    }

    private void answerCallback(String callbackQueryId) {
        try {
            AnswerCallbackQuery answer = new AnswerCallbackQuery();
            answer.setCallbackQueryId(callbackQueryId);
            answer.setShowAlert(false);
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "traumUrlaubBot";
    }

    @Override
    public String getBotToken() {
        return "7767441057:AAEFvRc8KTDEhxPWlwpSP-GzdfcC1rjyuzE";
    }

}