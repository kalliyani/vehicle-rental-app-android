<?php

include("connection.php");

$userId = $_POST['user_id'];
$carId = $_POST['car_id'];
$licenseno = $_POST['license_no'];
$noofdays = $_POST['no_of_days'];
$image = $_POST['image'];
$amount = $_POST['amount'];

$date = date('Y-m-d');

$totalAmount = $amount*$noofdays;

$sql = "INSERT INTO renting_tbl(userid,car_id,license_no,noofdays,booking_date,image,amount,status)VALUES('$userId','$carId','$licenseno','$noofdays','$date','$image','$totalAmount','booked')";

if(mysqli_query($con,$sql)){
	
	$id = mysqli_insert_id($con);
	$fileName = "image".$id.".jpg";
	file_put_contents("../booking_tbl/uploads/".$fileName,base64_decode($image));


	$sql = "UPDATE renting_tbl SET image = '$fileName' WHERE id = '$id'";
	mysqli_query($con,$sql);
	echo "success";

	}
else{
	
	echo"failed";
}

?>