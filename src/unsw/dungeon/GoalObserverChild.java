package unsw.dungeon;

public interface GoalObserverChild {
    void update(Goal g);
    void setParent(GoalObserverParent parent);
    
}