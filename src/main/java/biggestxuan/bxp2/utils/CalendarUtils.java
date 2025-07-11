package biggestxuan.bxp2.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;

/**
 * @Author Biggest_Xuan
 * 2025/4/3
 */
public class CalendarUtils {
    public static final CalendarUtils INSTANCE = new CalendarUtils();

    Calendar calendar = Calendar.getInstance();
    protected int year = calendar.get(Calendar.YEAR);
    protected int month = calendar.get(Calendar.MONTH)+1;
    protected int day = calendar.get(Calendar.DAY_OF_MONTH);

    public boolean isNewYear(){
        return month == 1 && day == 1;
    }

    public boolean isSpringFestival(){
        return isChineseFestival(1,1);
    }

    public boolean isLanternFestival(){
        return isChineseFestival(1,15);
    }

    public boolean isAprilFoolsDay(){
        return month == 4 && day == 1;
    }

    public boolean isWorkersDay(){
        return month == 5 && day == 1;
    }

    public boolean isDragonBoatFestival(){
        return isChineseFestival(5,5);
    }

    public int getHour(){
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public NationalUtils isNationalDay(){
        return new NationalUtils(month == 10 && day == 1,year);
    }

    public boolean isMidAutumnFestival(){
        return isChineseFestival(8,15);
    }

    public boolean isChristmasDay(){
        return month == 12 && day == 25;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    public record NationalUtils(boolean isNationalDay, int year) {
    }

    public String getNowTimeWelcome(){
        String s;
        LocalPlayer player = Minecraft.getInstance().player;
        if(player != null && new BirthdayUtils(player).HappyBirthday()){
            return "Happy Birthday";
        }
        int hour = getHour();
        if(hour >= 6 && hour < 12){
            s = "Good Morning";
        }else if(hour >= 12 && hour < 18){
            s = "Good Afternoon";
        } else if(hour >= 18 && hour <= 23){
            s = "Good Evening";
        }else{
            s = "Have A Good Dream";
        }
        return s;
    }

    public boolean isChineseFestival(int ChineseMonth, int ChineseDay){
        return false;
        /*ChineseDate date = new ChineseDate(year,ChineseMonth,ChineseDay);
        return date.getGregorianMonth() + 1 == this.month && date.getGregorianDay() == this.day;*/
    }
}
