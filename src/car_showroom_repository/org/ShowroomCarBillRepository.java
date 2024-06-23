package car_showroom_repository.org;

import java.sql.SQLException;
import java.util.ArrayList;

import car_showroom_config.org.DBHelper;
import car_showroom_model.org.ShowroomCarBillModel;

public class ShowroomCarBillRepository extends DBHelper{

	public boolean addDataInBill(ShowroomCarBillModel sCBillModel) {
		try
		{
			cs=conn.prepareCall(properties.getProperty("addDataIntoCarCustJoinAndBill"));
			cs.setInt(1, sCBillModel.getCarId());
			cs.setInt(2, sCBillModel.getCustId());
			cs.setInt(3, sCBillModel.getIncurance());
			cs.setLong(4, sCBillModel.getTotal());
			boolean b=cs.execute();
			return !b;
		}
		catch(Exception ex)
		{
			System.out.println("error in Add data In Bill "+ex);
		}
		
		return false;
	}

	public int getTotalOrds() {
		try {
			pstmt=conn.prepareStatement(properties.getProperty("getTotalOrds"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception ex) {
			System.out.println("Ex");
			return 0;
		}
	}
	
	public ArrayList<Long> getMonthWiseSell(String startDate)
	{
		ArrayList<Long> al= new ArrayList<Long>();
		try
		{
			String arr[]=startDate.split("-");//2024-04-01
			arr[2]="1";
			String fromDate=String.join("-",arr);
			
			String curdate=this.getcurdate();//2024-06-01
			String arr1[]=curdate.split("-");
			arr1[1]=String.valueOf(Integer.parseInt(arr1[1]));
			arr1[2]="1";
			String ToDate=String.join("-",arr1);
			
			String varDate=fromDate;
			pstmt=conn.prepareStatement(properties.getProperty("getMonthWiseSell"));
			while(!varDate.equals(ToDate))
			{
				//System.out.println(varDate+" "+ToDate);
				String arr3[]=varDate.split("-");//2024-04-01
				int n=Integer.parseInt(arr3[1])+1;
				if(n>12)
				{
					int year=Integer.parseInt(arr3[0])+1;
					n=1;
					arr3[0]=String.valueOf(year);
					arr3[1]=String.valueOf(n);
				}
				else
				{
					arr3[1]=String.valueOf(n);
				}
				varDate=String.join("-",arr3);
				pstmt.setString(1, fromDate);
				pstmt.setString(2, varDate);
//				System.out.println("f "+fromDate);
//				System.out.println("vardate "+varDate);
				pstmt.setInt(3, n);
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					al.add(rs.getLong(1));
				}	
				
			}
			return al;
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			ex.printStackTrace();
		}
		return null;
	}
	public String getcurdate()
	{
		try {
			pstmt=conn.prepareStatement("select curdate();");
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				return String.valueOf(rs.getDate(1));
			}
			else {
				return null;
			}
			
			//return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
