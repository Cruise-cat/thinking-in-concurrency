package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#getRegisteredParties()} 和 {@link Phaser#register()} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#getRegisteredParties()
 * @see Phaser#register()
 * @since 2020/8/7
 */
public class PhaserDemo6 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());

        phaser.register();
        System.out.println(phaser.getRegisteredParties());
    }
}
/**
 * 5
 * 6
 * 7
 * 8
 * 9
 */
