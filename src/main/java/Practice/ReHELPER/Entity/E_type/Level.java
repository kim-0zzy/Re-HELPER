package Practice.ReHELPER.Entity.E_type;

public enum Level {
    INTERMEDIATE(6, null),
    NOVICE(3,INTERMEDIATE),
    BEGINNER(1,NOVICE),
    UNTRAINED(0,BEGINNER);

    private Integer career;
    private Level nextLevel;

    Level(Integer career, Level nextLevel) {
        this.career = career;
        this.nextLevel = nextLevel;
    }

    public static boolean availableLevelUp(Level level, Integer career){
        if(level.nextLevel == null){
            return false;
        }
        return level.nextLevel.career <= career;
    }

    public static Level levelUp(Level level){
        return level.nextLevel;
    }

}
