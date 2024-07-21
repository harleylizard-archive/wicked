package com.harleylizard.wicked;

import com.harleylizard.wicked.common.block.BlockCandle;
import com.harleylizard.wicked.common.block.Color;
import org.junit.jupiter.api.Test;

public final class Tests {

    @Test
    public void candleTest() {
        int meta = BlockCandle.pack(Color.WHITE.ordinal(), true);

        int color = BlockCandle.getColor(meta);
        boolean lit = BlockCandle.isLit(meta);

        System.out.println(color);
        System.out.println(lit);
    }
}
