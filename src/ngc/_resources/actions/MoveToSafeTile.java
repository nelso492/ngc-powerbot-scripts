package ngc._resources.actions;

import ngc._resources.actions._template.BaseAction;
import ngc._resources.functions.CommonFunctions;
import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class MoveToSafeTile extends BaseAction<ClientContext> {

    private Tile safeTile;

    public MoveToSafeTile(ClientContext ctx, Tile safeTile) {
        super(ctx, "Moving");
        this.safeTile = safeTile;
    }

    @Override
    public boolean activate() {
        return ctx.players.local().interacting().valid() && safeTile.distanceTo(ctx.players.local()) > 0 && !ctx.players.local().inMotion();
    }


    @Override
    public void execute() {
        if( safeTile.matrix(ctx).inViewport() ) {
            CommonFunctions.walkToSafespot(ctx, safeTile);

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return safeTile.distanceTo(ctx.players.local()) == 0;
                }
            }, 250, 20);
        } else {
            ctx.movement.step(safeTile);
        }
    }

}