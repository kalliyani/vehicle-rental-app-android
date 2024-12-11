<?php

include("connection.php");

$bookingid = $_POST['bid'];

$sql = "DELETE FROM renting_tbl WHERE id ='$bookingid'";
mysqli_query($con,$sql);

if(mysqli_query($con,$sql)){

	echo "success";
}else{

	echo "failed";
}

?>