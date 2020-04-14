package com.PAF.PAFGrpProject.Laboratory_Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaboratoryRepository {

	Connection con = null;

	Laboratory lab1 = new Laboratory();

	public LaboratoryRepository() {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "root";
		String password = "Akbar.1806360";

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Laboratory> getLaboraotries() { // this function will get the
												// laboratories inserted in the
												// database

		List<Laboratory> labs = new ArrayList<>();

		String sql = "SELECT * FROM laboratory";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Laboratory lab1 = new Laboratory();

				lab1.setIdLaboratory(rs.getString(1));
				lab1.setLabName(rs.getString(2));
				lab1.setAddress(rs.getString(3));
				lab1.setTelNo(rs.getInt(4));
				lab1.setCity(rs.getString(5));

				labs.add(lab1);

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return labs;
	}

	public Laboratory getLaboraotory(String labID) { // this function will get
														// the required
														// laboratory from the
														// database

		String sql = "SELECT * FROM laboratory WHERE idLaboratory=" + labID;

		Laboratory lab = new Laboratory(); // creating a laboratory object

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {

				lab.setIdLaboratory(rs.getString(1));
				lab.setLabName(rs.getString(2));
				lab.setAddress(rs.getString(3));
				lab.setTelNo(rs.getInt(4));
				lab.setCity(rs.getString(5));

			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return lab;
	}

	public void createLaboratory(Laboratory lab) {
		// this function will crate a new laboratory

		String sql = "INSERT INTO laboratory VALUES(?,?,?,?,?)";

		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, lab.getIdLaboratory());
			st.setString(2, lab.getLabName());
			st.setString(3, lab.getAddress());
			st.setInt(4, lab.getTelNo());
			st.setString(5, lab.getCity());

			st.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateLaboratory(Laboratory lab) {
		// this function will update the relevant laboratory

		String sql = "UPDATE laboratory SET name=?, address=?, telNo=?, city=? WHERE idLaboratory=?";

		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, lab.getLabName());
			st.setString(2, lab.getAddress());
			st.setInt(3, lab.getTelNo());
			st.setString(4, lab.getCity());
			st.setString(5, lab.getIdLaboratory());

			st.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void deleteLaboratory(String id) {

		String sql = "DELETE FROM laboratory WHERE idLaboratory=?";

		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			st.executeUpdate();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
