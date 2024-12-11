<?php

include("connection.php");

$uid = $_POST['uid'];
$feedback = $_POST['feedback'];


$sql ="INSERT INTO feedback_tbl(userid,feedback) VALUES('$uid','$feedback')";

if(mysqli_query($con,$sql)){
	
	echo"success";
}
else{
	
	echo"failed";
}


?>