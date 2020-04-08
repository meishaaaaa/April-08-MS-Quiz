package Repository;

import java.sql.*;
import java.util.List;

public class ParkingRepository {

    //清空所在表的所有数据
    public static void clearHistory() {
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("TRUNCATE TABLE park")) {
            ps.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    //初始化停车场数据，用stream的方法创建
    public static void initializePark(List<String> parkingIds) {
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("insert into park(stall_num) values(?)")) {
            for (String parkId : parkingIds) {
                ps.setString(1, parkId);
                ps.executeUpdate();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    //储存停车的车牌号
    public static void parkCar(ParkInfo car) {
        String sql = "update park set plate_num = ?, parking_start_time=?  where pkid=(select pkid from (select pkid from park Where plate_num is null Limit 1) temp)";

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getPlateNumber());
            ps.setTimestamp(2, new Timestamp(car.getParkingStartTime().getTime()));

            ps.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public static String parkInfo(String plateNumber) {
        String sql = String.format("select stall_num from park where plate_num = '%s'", plateNumber);
        String parkInfo = null;
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                parkInfo = rs.getString("stall_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkInfo;
    }

 */

    public static ParkInfo getInfo(String plateNumber) {
        ParkInfo info = new ParkInfo();
        info.setPlateNumber(plateNumber);
        String sql = String.format("select stall_num, parking_start_time from park where plate_num = '%s'", plateNumber);

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                info.setStallNumber(rs.getString("stall_num"));
                info.setParkingStartTime(rs.getTimestamp("parking_start_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    /*
    public static ParkInfo getInfoByStall(String stallNumber) {
        ParkInfo info = new ParkInfo();
        info.setPlateNumber(stallNumber);
        String sql = String.format("select plate_num, parking_start_time from park where stall_num = '%s'", stallNumber);

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                info.setPlateNumber(rs.getString("plate_num"));
                info.setParkingStartTime(rs.getTimestamp("parking_start_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

     */


    //是否满了
    public static boolean checkFulled() {
        boolean flg = true;
        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("select count(*) from park where plate_num is null ");
             ResultSet rs = ps.executeQuery()
        ) {
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                flg = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flg;
    }


    //输入停车卷，并删除此行数据
    public static void refresh(String plateNumber) {

        try (Connection con = DbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(String.format("update park set plate_num = null, parking_start_time = null where plate_num ='%s'", plateNumber))) {
            ps.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }
}

