package shared.actions;


import shared.constants.Items;
import shared.templates.AbstractAction;
import shared.tools.GaussianTools;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

import static org.powerbot.script.Condition.sleep;

/**
 * Dwarven rock cake guzzle action for NMZ
 */
public class GuzzleRockCake extends AbstractAction<ClientContext> {

    public GuzzleRockCake(ClientContext ctx, String status) {
        super(ctx, status);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(Items.DWARVEN_ROCK_CAKE_7510).count() == 1 &&
                ctx.combat.health() > 1 && ctx.combat.health() < 5;
    }

    @Override
    public void execute() {
        boolean guzzle;

        switch( ctx.combat.health() ) {
            case 2:
                guzzle = GaussianTools.takeActionNormal();
                break;
            case 3:
                guzzle = !GaussianTools.takeActionUnlikely();
                break;
            case 4:
                guzzle = !GaussianTools.takeActionNever();
                break;
            default:
                guzzle = true;
        }

        if( guzzle ) {
            sleep(Random.nextInt(600, 1200));
            while(ctx.combat.health() > 1) {
                ctx.inventory.select().id(Items.DWARVEN_ROCK_CAKE_7510).poll().interact("Guzzle");
                sleep();
            }
        }
    }
}
