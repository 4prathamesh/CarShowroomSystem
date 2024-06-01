package car_showroom_repository.org;

import java.util.ArrayList;

import car_showroom_config.org.DBHelper;
import car_showroom_model.org.CarMasterModel;
import car_showroom_model.org.ShowroomInsuranceModel;
import java.util.*;
public class ShowroomInsuranceRepository extends DBHelper{

	public boolean setInsuranceInfo(ShowroomInsuranceModel shInsModel) {
		// TODO Auto-generated method stub
		try
		{
			pstmt=conn.prepareStatement("insert into insuranceMaster values ('0',?,?)");
			pstmt.setString(1, shInsModel.getName());
			pstmt.setLong(2,shInsModel.getPrice());
			return pstmt.executeUpdate()>0 ? true: false ;
		}
		catch(Exception ex)
		{
			System.out.println("error in setInsuranceInfo method "+ex);
			return false;
		}
		
	}

	public ArrayList<ShowroomInsuranceModel> getInsuranceList() {
		// TODO Auto-generated method stub
		ArrayList<ShowroomInsuranceModel> al= new ArrayList<ShowroomInsuranceModel>();
		try
		{
			pstmt=conn.prepareStatement("select *from insuranceMaster");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				ShowroomInsuranceModel shInsModel=new ShowroomInsuranceModel();
				shInsModel.setInsId(rs.getInt(1));
				shInsModel.setName(rs.getString(2));
				shInsModel.setPrice(rs.getLong(3));
				al.add(shInsModel);
			}
			return al.size()>0 ? al:null;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error in Get Insurance List metod "+ex);
			return null;
		}
		
	}

	public ShowroomInsuranceModel getInsurancePrice(int insId) {
		// TODO Auto-generated method stub
//		ShowroomInsuranceModel shInsModel= new ShowroomInsuranceModel();
		try
		{
			pstmt=conn.prepareStatement("select *from insuranceMaster where insId=?");
			pstmt.setInt(1, insId);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				
				return new ShowroomInsuranceModel(rs.getString(2),rs.getLong(3));
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error in Get price metode "+ex);
			
		}
		return null;
	}

}
