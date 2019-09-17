package com.liandi.design.mode.decker;

import org.junit.Test;

import com.liandi.design.mode.decker.base.Beverage;

/**
 * 装饰者模式测试类
 * 
 * @author czg
 * @date 2019/9/16 19:12
 */
public class DeckerTest {

    @Test
    public void testDecker() {

        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());

    }

}
