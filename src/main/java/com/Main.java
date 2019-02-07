package com;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        Links html = new Links.HTML(args[0], new HTTP.Default());
        System.out.println(html.toString());
    }
}
