package ngc.slayer_simple;

import ngc._resources.Items;
import ngc._resources.actions._template.BaseAction;
import ngc._resources.functions.CommonFunctions;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class BonesToPeaches extends BaseAction<ClientContext> {

    private int minBoneCount;
    private int boneId;

    public BonesToPeaches(ClientContext ctx, int minBoneCount, int boneId) {
        super(ctx, "Bones2Peaches");
        this.minBoneCount = minBoneCount;
        this.boneId = boneId;
    }

    @Override
    public boolean activate() {
        int boneCount = ctx.inventory.select().id(boneId).count();
        int foodCount = ctx.inventory.select().id(CommonFunctions.allFoodIds()).count();

        return boneCount >= minBoneCount && foodCount == 0;
    }


    @Override
    public void execute() {
        Item tab = ctx.inventory.select().id(Items.BONES_TO_PEACHES_8015).first().poll();

        if( tab.valid() ) {
            tab.interact("Break", tab.name());

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(Items.PEACH_6883).count() > 2;
                }
            }, 100, 20);
        }
    }

}