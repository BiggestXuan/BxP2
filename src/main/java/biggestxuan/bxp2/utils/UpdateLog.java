package biggestxuan.bxp2.utils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Biggest_Xuan
 * 2025/6/28
 */
public record UpdateLog(String version,int add,int modify,int fix,int other,String updateTime) {
    public enum UpdateLogs{
        A1(new UpdateLog("0.0.1",3,0,0,0,"2025-4-2")),
        A2(new UpdateLog("0.0.2",4,0,0,0,"2025-4-3")),
        A3(new UpdateLog("0.0.3",2,0,0,0,"2025-4-4")),
        A4(new UpdateLog("0.0.4",0,1,0,1,"2025-4-5")),
        A5(new UpdateLog("0.0.5",2,0,0,1,"2025-4-6")),
        A6(new UpdateLog("0.0.6",3,2,0,0,"2025-4-7")),
        A7(new UpdateLog("0.0.7",5,8,0,0,"2025-4-8")),
        A8(new UpdateLog("0.0.8",1,4,1,2,"2025-4-9")),
        A9(new UpdateLog("0.0.9",4,1,0,0,"2025-4-10")),
        A10(new UpdateLog("0.0.10",3,6,0,0,"2025-4-11")),
        A11(new UpdateLog("0.0.11",2,3,0,1,"2025-4-12")),
        A12(new UpdateLog("0.0.12",1,0,0,0,"2025-4-13")),
        A13(new UpdateLog("0.0.13",4,5,0,1,"2025-4-14")),
        A14(new UpdateLog("0.0.14",5,3,0,0,"2025-4-15")),
        A15(new UpdateLog("0.0.15",7,9,0,0,"2025-4-16")),
        A16(new UpdateLog("0.0.16",4,4,0,0,"2025-4-17")),
        A17(new UpdateLog("0.0.17",7,2,1,0,"2025-4-18")),
        A18(new UpdateLog("0.0.18",5,6,0,0,"2025-4-19")),
        A19(new UpdateLog("0.0.19",3,3,0,0,"2025-4-20")),
        A20(new UpdateLog("0.0.20",2,5,0,0,"2025-4-21")),
        A21(new UpdateLog("0.0.21",1,1,0,0,"2025-4-22")),
        A22(new UpdateLog("0.0.22",3,2,1,2,"2025-4-23")),
        A23(new UpdateLog("0.0.23",7,9,0,0,"2025-4-24")),
        A24(new UpdateLog("0.1.0",0,1,0,1,"2025-4-25")),
        A25(new UpdateLog("0.1.1",2,10,5,2,"2025-4-25")),
        A26(new UpdateLog("0.1.2",3,9,4,0,"2025-4-26")),
        A27(new UpdateLog("0.2.0",6,9,4,0,"2025-4-29")),
        A28(new UpdateLog("0.3.0",10,24,8,0,"2025-5-7")),
        A29(new UpdateLog("0.4.0",12,14,2,0,"2025-5-11")),
        A30(new UpdateLog("0.5.0",5,6,2,0,"2025-5-14")),
        A31(new UpdateLog("0.6.0",14,9,2,0,"2025-6-15")),
        A32(new UpdateLog("0.7.0",9,5,5,0,"2025-6-30")),
        A33(new UpdateLog("0.7.1",0,2,1,0,"2025-7-1")),
        A34(new UpdateLog("0.7.2",1,10,1,0,"2025-7-2")),
        A35(new UpdateLog("1.0.0-pre1",11,23,5,0,"2025-7-13")),
        ;
        public final UpdateLog log;

        UpdateLogs(UpdateLog log){
            this.log = log;
        }
    }

    public enum Mode{
        ADD("add"),MODIFY("modify"),FIX("fix"),OTHER("other");

        public final String s;
        Mode(String s){
            this.s = s;
        }
    }

    public static List<UpdateLog> getAllLogs(){
        List<UpdateLog> list = new ArrayList<>();
        for(UpdateLogs ls : UpdateLogs.values()){
            list.add(ls.log);
        }
        Collections.reverse(list);
        return list;
    }

    @Nullable
    public static UpdateLog getLog(String version){
        for(UpdateLog ul : getAllLogs()){
            if(version.equals(ul.version)){
                return ul;
            }
        }
        return null;
    }
}
