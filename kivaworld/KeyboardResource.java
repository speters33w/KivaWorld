package kivaworld;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class KeyboardResource {
    private final Scanner scanner;

    public KeyboardResource() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
    }

    public String getLine() {
        return this.scanner.nextLine();
    }
}
