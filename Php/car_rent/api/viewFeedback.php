<?php

include("connection.php");

$uid = $_REQUEST['uid'];

$sql ="SELECT * FROM feedback_tbl WHERE userid !='$uid'";
$result = mysqli_query($con,$sql);

if (mysqli_num_rows($result) > 0) {

	while ($row = mysqli_fetch_assoc($result)){

		$sql2 = "SELECT * FROM user_tbl WHERE id='$row[userid]'";
		$result2 = mysqli_query($con,$sql2);
		$row2 = mysqli_fetch_assoc($result2);


		$data["data"][] = array('id'=> $row['id'],'name'=> $row2['name'],'feedback'=> $row['feedback']);
	}
	echo json_encode($data);
}
else{

	echo "failed";
}

?>