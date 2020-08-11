package com.cruise.thinking.in.concurrency.phaser;

import java.util.concurrent.Phaser;

/**
 * {@link Phaser#bulkRegister(int)} 示例
 *
 * @author Cruise
 * @version 1.0
 * @see Phaser#bulkRegister(int)
 * @since 2020/8/7
 */
public class PhaserDemo7 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(10);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);
        System.out.println(phaser.getRegisteredParties());
    }
}
/**
 * 10
 * 20
 * 30
 * 40
 * 50
 */
