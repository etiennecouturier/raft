package command;

import board.Board;

public class NoOp extends Command {
    @Override
    protected boolean isApplicable(Board board) {
        return false;
    }

    @Override
    protected void execute(Board board) {

    }
}
