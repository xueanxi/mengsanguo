package mengsanguo.anxi.com.mengsanguo.model;

import java.util.ArrayList;

/**
 * 角色基础模型
 */
public class PlayerModel {
    private static final String TAG = "PlayerModel";

    // 基础资料
    private float aptitude; // 资质（影响每次升级 基础属性的增加数量 资质取值为1～5 1为庸碌无为 5为天神下凡）
    private String name;    // 名字
    private int sexuality;  // 男1 女0

    /**
     * 基础属性
     * 每升一级会获得五项基础属性的提升
     * 提升的公式为：
     * newValues = oldVaules + ( Values_Initial*0.3 + aptitude*0.7 )
     * 文字描述： 新属性 = 旧属性 + （ 0.3乘以 该属性的初始属性 + 资质 乘以 0.7）
     */
    // 以下5个属性，会随等级提高
    private int strength;       // 力量  （影响 少量HP、大量物理伤害、少量护甲、躲闪概率、少量生命恢复）
    private int intellect;      // 智力  （影响 大量魔法伤害、少量魔抗）
    private int dexterity;      // 敏捷  （影响 速度、中量物理伤害、真实伤害、大量命中率、大量闪避、大量速度、中量暴击率）
    private int physique;       // 体魄  （影响 大量HP、大量护甲、少量魔抗、大量生命恢复、少量魅力、少量格档）
    private int spirit;         // 精神  （影响 大量魔抗，少量魔法伤害，少量暴击率，少量闪避概率，少量格档概率、少量生命恢复、少量魔法伤害）

    /**
     * 初始属性 即0级时的属性，这个属性衡量了一个人物的天赋
     * 初始属性 和 资质 共同影响 升级后提升属性的值
     */
    private int strength_Initial;       //初始力量
    private int intellect_Initial;      //初始智力
    private int dexterity_Initial;      //初始敏捷
    private int physique_Initial;       //初始体魄
    private int spirit_Initial;         //初始精神

    //运气为固定属性，不会随等级提高
    private int luck;//运气       （影响 暴击率、被暴击率、格档概率、闪避概率、命中率、优秀装备和物品的爆率）

    /**
     * 技能
     */
    ArrayList<SkillModel> skillLists = null;
    private int level = 0;//等级

    /**
     * 战斗属性
     */
    private int HP;                 // 生命值
    private int experiencePoint;    // 经验值
    private int physicDamage;       // 物理伤害
    private int magicDamage;        // 魔法伤害
    private int realDamage;         // 真实伤害
    private int physicsPenetrate;   // 物理穿透
    private int magicPenetrate;     // 魔法穿透
    private int accuracy;           // 命中率
    private int criteRate;          // 暴击率
    private int reduceBeCriteRate;  // 减少被暴击率
    private int criteDamage;        // 暴击伤害
    private int armor;              // 护甲（物抗）
    private int magicResist;        // 魔抗
    private int dodge;              // 闪避（闪避成功承受1%的物理伤害 或者 承受30%的魔法伤害）
    private int block;              // 格档几率（格档成功只承受30%物理伤害 或者 承受70%魔法伤害）
    private int speed;              // 速度
    private int hpRestore;          // 生命恢复
    private int fascination;        // 魅力（影响 收服武将的概率、商品购买和出售的价格、增加对异性的伤害、增加对异性的闪避）


    // 战斗属性，只有在战斗时才会有的属性
    private int activeValues;       // 行动值，当行动值满了之后，就可以行动。
    private int battleId;           // 一场战斗中分配的id
    private int camp;               // 阵营

    public PlayerModel(int strength, int intellect, int dexterity, int physique, int spirit, int fascination, int luck, boolean isBoy) {
        this.strength = strength;        // 力量
        this.intellect = intellect;      // 智力
        this.dexterity = dexterity;      // 敏捷
        this.physique = physique;        // 体魄
        this.spirit = spirit;            // 精神
        this.fascination = fascination;
        this.luck = luck;

        HP = this.strength * 2 + this.physique * 5;                // 生命值
        experiencePoint = 0;                             // 经验值
        physicDamage = this.strength * 3 + this.dexterity * 1;     // 物理伤害
        magicDamage = this.intellect * 3 + this.spirit * 1;        // 魔法伤害
        realDamage = this.dexterity / 2;                      // 真实伤害
        physicsPenetrate = 0;                            // 物理穿透
        magicPenetrate = 0;                              // 魔法穿透
        accuracy = this.strength + this.intellect * 2 + this.dexterity * 3 + this.spirit * 1 + this.physique + (int) (this.luck * 0.5);//命中率
        criteRate = this.dexterity * 1 + (int) (this.luck * 0.5f); //暴击率
        criteDamage = (int) (200 + (this.physique + this.spirit) * 0.1);//暴击伤害
        armor = this.strength + this.physique * 3;                //护甲（物抗）
        magicResist = intellect * 2 + this.physique + this.spirit * 3;//魔抗
        dodge = this.dexterity * 2 + this.spirit + (int) (this.luck * 0.5f);//闪避（闪避成功承受10%的物理伤害 或者 承受30%的魔法伤害）
        block = this.strength * 3 + this.physique * 2;//格档几率（格档成功只承受30%物理伤害 或者 承受70%魔法伤害）
        speed = this.dexterity * 3 + this.physique;//速度
        hpRestore = this.strength + this.physique * 3 + this.spirit;  // 生命恢复
        sexuality = isBoy ? 1 : 0; // 男1 女0
    }

    public float getAptitude() {
        return aptitude;
    }

    public void setAptitude(float aptitude) {
        this.aptitude = aptitude;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getPhysique() {
        return physique;
    }

    public void setPhysique(int physique) {
        this.physique = physique;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public int getPhysicDamage() {
        return physicDamage;
    }

    public void setPhysicDamage(int physicDamage) {
        this.physicDamage = physicDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getRealDamage() {
        return realDamage;
    }

    public void setRealDamage(int realDamage) {
        this.realDamage = realDamage;
    }

    public int getPhysicsPenetrate() {
        return physicsPenetrate;
    }

    public void setPhysicsPenetrate(int physicsPenetrate) {
        this.physicsPenetrate = physicsPenetrate;
    }

    public int getMagicPenetrate() {
        return magicPenetrate;
    }

    public void setMagicPenetrate(int magicPenetrate) {
        this.magicPenetrate = magicPenetrate;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getCriteRate() {
        return criteRate;
    }

    public void setCriteRate(int criteRate) {
        this.criteRate = criteRate;
    }

    public int getReduceBeCriteRate() {
        return reduceBeCriteRate;
    }

    public void setReduceBeCriteRate(int reduceBeCriteRate) {
        this.reduceBeCriteRate = reduceBeCriteRate;
    }

    public int getCriteDamage() {
        return criteDamage;
    }

    public void setCriteDamage(int criteDamage) {
        this.criteDamage = criteDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist = magicResist;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    public void setHpRestore(int hpRestore) {
        this.hpRestore = hpRestore;
    }

    public int getFascination() {
        return fascination;
    }

    public void setFascination(int fascination) {
        this.fascination = fascination;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getSexuality() {
        return sexuality;
    }

    public void setSexuality(int sexuality) {
        this.sexuality = sexuality;
    }

    public int getActiveValues() {
        return activeValues;
    }

    public void setActiveValues(int activeValues) {
        this.activeValues = activeValues;
    }

    public int getBattleId() {
        return battleId;
    }

    public void setBattleId(int battleId) {
        this.battleId = battleId;
    }

    public ArrayList<SkillModel> getSkillLists() {
        return skillLists;
    }

    public void setSkillLists(ArrayList<SkillModel> skillLists) {
        this.skillLists = skillLists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength_Initial() {
        return strength_Initial;
    }

    public void setStrength_Initial(int strength_Initial) {
        this.strength_Initial = strength_Initial;
    }

    public int getIntellect_Initial() {
        return intellect_Initial;
    }

    public void setIntellect_Initial(int intellect_Initial) {
        this.intellect_Initial = intellect_Initial;
    }

    public int getDexterity_Initial() {
        return dexterity_Initial;
    }

    public void setDexterity_Initial(int dexterity_Initial) {
        this.dexterity_Initial = dexterity_Initial;
    }

    public int getPhysique_Initial() {
        return physique_Initial;
    }

    public void setPhysique_Initial(int physique_Initial) {
        this.physique_Initial = physique_Initial;
    }

    public int getSpirit_Initial() {
        return spirit_Initial;
    }

    public void setSpirit_Initial(int spirit_Initial) {
        this.spirit_Initial = spirit_Initial;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public static class Column {
        public static final String tableName = "tableName";
        public static final String id = "_id";

        public static final String name = "name";
        public static final String sexuality = "sexuality";
        public static final String aptitude = "aptitude";
        public static final String level = "level";

        public static final String strength_Initial = "strength_Initial";
        public static final String intellect_Initial = "intellect_Initial";
        public static final String dexterity_Initial = "dexterity_Initial";
        public static final String physique_Initial = "physique_Initial";
        public static final String spirit_Initial = "spirit_Initial";

        public static final String strength = "strength";
        public static final String intellect = "intellect";
        public static final String dexterity = "dexterity";
        public static final String physique = "physique";
        public static final String spirit = "spirit";
        public static final String luck = "luck";
        public static final String skillLists = "skillLists";


        public static final String HP = "HP";
        public static final String experiencePoint = "experiencePoint";
        public static final String physicDamage = "physicDamage";
        public static final String magicDamage = "magicDamage";
        public static final String realDamage = "realDamage";
        public static final String physicsPenetrate = "physicsPenetrate";
        public static final String magicPenetrate = "magicPenetrate";
        public static final String accuracy = "accuracy";
        public static final String criteRate = "criteRate";
        public static final String reduceBeCriteRate = "reduceBeCriteRate";
        public static final String criteDamage = "criteDamage";
        public static final String armor = "armor";
        public static final String magicResist = "magicResist";
        public static final String dodge = "dodge";
        public static final String block = "block";
        public static final String speed = "speed";
        public static final String hpRestore = "hpRestore";
        public static final String fascination = "fascination";
        public static final String activeValues = "activeValues";
        public static final String battleId = "battleId";
        public static final String camp = "camp";
    }
}
