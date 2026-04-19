public class ConsoleUtils {
    private static final int TERMINAL_WIDTH = 120;

    public static void printCentered(String text) {
        int padding = (TERMINAL_WIDTH - text.length()) / 2;
        if (padding > 0) {
            System.out.println(" ".repeat(padding) + text);
        } else {
            System.out.println(text);
        }
    }

    public static void printCenteredInput(String text) {
        int padding = (TERMINAL_WIDTH - text.length()) / 2;
        if (padding > 0) {
            System.out.print(" ".repeat(padding) + text);
        } else {
            System.out.print(text);
        }
    }

    public static String getPadding(int textLength) {
    int width = 120;
    int padding = (width - textLength) / 2;
    return (padding > 0) ? " ".repeat(padding) : "";
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 30; i++) System.out.println();
        }
    }
    
    public static void printLine() {
        printCentered("═".repeat(50));
    }
}