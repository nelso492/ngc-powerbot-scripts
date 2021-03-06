package scripts.cooking_lumby_castle.phases;

import org.powerbot.script.rt4.ClientContext;
import scripts.cooking_lumby_castle.actions.AscendStairs;
import scripts.cooking_lumby_castle.actions.WalkKitchenToStairs;
import shared.templates.AbstractPhase;
import shared.templates.StructuredPhase;

public class AscendStairsPhase extends StructuredPhase {

    private final WalkKitchenToStairs walkKitchenToStairsAction;
    private final AscendStairs ascendStairsAction;

    public AscendStairsPhase(ClientContext ctx) {
        super(ctx, "CLIMB UP");

        this.walkKitchenToStairsAction = new WalkKitchenToStairs(ctx);
        this.ascendStairsAction = new AscendStairs(ctx);

        this.walkKitchenToStairsAction.setNextAction(this.ascendStairsAction);

        this.setInitialAction(this.walkKitchenToStairsAction);
    }


    @Override
    public boolean moveToNextPhase() {
        return ctx.game.floor() == 2;
    }


}
