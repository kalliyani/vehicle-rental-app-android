<?php

include("connection.php");


$uid = $_POST['uid'];
$bookingid = $_POST['bid'];
$amount = $_POST['amount'];
$cardnumber= $_POST['cardnumber'];
$date = date('Y-m-d');


$sql = "INSERT INTO payment_tbl(user_id,bookingid,amount,cardnumber,date)VALUES('$uid','$bookingid','$amount','$cardnumber','$date')";
if(mysqli_query($con,$sql))
{
	$sql = "UPDATE renting_tbl SET status = 'paid' WHERE id = '$bookingid'";
	mysqli_query($con,$sql);
  echo "success";
}else{

	echo "failed";
}
		  
?>