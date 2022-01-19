package concurrenttest.executor.pazzle;

import java.util.Set;

public interface Puzzle<P, M> {

    /** 初始化 position */
    P initialPosition();

    /** 是否是目标 */
    boolean isGoal(P position);

    /** 合法的移动 */
    Set<M> legalMoves(P position);

    /** 移动 */
    P move(P position, M move);
}
