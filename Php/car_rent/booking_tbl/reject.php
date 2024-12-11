<?php
include("../connection.php");

$up="Update renting_tbl set status='Rejected' where id='$_REQUEST[id]'";
//echo $up;
$res=mysqli_query($con,$up);

header('location:viewbookings.php');


?>