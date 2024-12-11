<?php


include("connection.php");

$name = $_POST['name'];
$number = $_POST['number'];
$email = $_POST['email'];
$password = $_POST['password'];


$sql = "INSERT INTO user_tbl(name,number,email,password) Values('$name','$number','$email','$password')";



if(mysqli_query($con,$sql)){

	echo "success";
}else{

	echo "failed";
}
?>