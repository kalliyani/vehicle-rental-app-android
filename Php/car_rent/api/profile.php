<?php

include("connection.php");

$id = $_POST['id'];
//$id = "1";

$sql = "SELECT * FROM user_tbl WHERE id ='$id'";
$result = mysqli_query($con,$sql);

while ($row = mysqli_fetch_assoc($result)) 
{
	$data['data'][] = $row;
}

echo json_encode($data);

?>