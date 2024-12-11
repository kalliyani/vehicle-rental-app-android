<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$sql ="SELECT * FROM renting_tbl WHERE userid ='$uid' and status !='paid'";
$result = mysqli_query($con,$sql);

if (mysqli_num_rows($result) > 0) {

	while ($row = mysqli_fetch_assoc($result)){

		$sql2 = "SELECT * FROM car_tbl WHERE id='$row[car_id]'";
		$result2 = mysqli_query($con,$sql2);
		$row2 = mysqli_fetch_assoc($result2);


		$data["data"][] = array('id'=> $row['id'],'noofdays'=> $row['noofdays'],'booking_date'=> $row['booking_date'],'status' => $row['status'],'amount'=> $row['amount'],'carname'=> $row2['name'],'carmodel'=> $row2['model'],'carimage'=> $row2['image']);
	}
	echo json_encode($data);
}
else{

	echo "failed";
}

?>