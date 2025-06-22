package biggestxuan.bxp2.utils;

import net.minecraft.world.entity.player.Player;

/**
 * @Author Biggest_Xuan
 * 2025/6/15
 */
public enum Sponsors {
    Biggest_Xuan(new Sponsor("Biggest_Xuan","29328b6c-6f03-4fba-9436-678b696e8aeb",5)),
    DCTOR_0415 (new Sponsor("Dctor_0415","4b2a8226-3c3a-4d3d-a26d-68b577ae1463",5)),
    sdxhop (new Sponsor("sdxhop","4f25e2d3-2cd4-45ce-b83d-8a965fe2137b",5)),
    _Btmy_ (new Sponsor("_Btmy_","ec54ae0b-a148-4408-8ff8-0661ab44fed0",5)),
    dytlj7788 (new Sponsor("dytlj7788","e690ea7a-8fce-4049-80dd-07158cd6a348",5)),
    Chara_SS (new Sponsor("Chara_SS","1738cb1b-ea69-4e0f-8678-688aea7e8d1b",5)),
    TULYE (new Sponsor("Tulye","52a0f9c3-5551-4de9-bfe5-16f803f44633",6)),
    xiangshushumiao (new Sponsor("xiangshushumiao","19cd7e09-e249-4b92-b35a-770b3399a302",4)),
    yuluo_1("yuluo_1","1719c509-2b62-47a4-a080-bef535fa1e68",4),
    MCyunxi (new Sponsor("xiao_yunxi","eb91acd8-a70e-4b1d-b1c4-34fc4c8af495",3)),
    HIEHEIHEICAT (new Sponsor("HIEHEIHEICAT","28f6f584-5d9c-45ba-b919-a8ebabf53477",3)),
    CSZXYMZX (new Sponsor("cszxymzx","e99f4af7-c050-4552-9627-6f80b5f0a0d3",3)),
    ABunana (new Sponsor("Abunana","6105dab8-94a6-440f-b797-00d046587eda",1)),
    LITTLE_YU(new Sponsor("LITTLE_YU","b6526df8-cb57-48f5-bbb7-0e4b0653f2c6",1)),
    SKY_LIN(new Sponsor("Sky_Lin","5f35fb1d-c990-4328-8d8f-ff6962490dd8",2))
    ;

    private final Sponsor sponsor;
    Sponsors(Sponsor sponsor){
        this.sponsor = sponsor;
    }

    Sponsors(String name, String uuid, int index){
        this.sponsor = new Sponsor(name, uuid, index);
    }

    Sponsors(Player player){
        this(player.getScoreboardName(),player.getStringUUID(),0);
    }

    public Sponsor getSponsors() {
        return sponsor;
    }

    private record Sponsor(String name, String uuid, int index){
        @Override
        public boolean equals(Object e){
            if(e instanceof Sponsor){
                Sponsor object = (Sponsor) e;
                return object.index == this.index && object.name().equals(this.name()) && object.uuid().equals(this.uuid());
            }
            return false;
        }
    }
}
