<?php
$email = $_REQUEST['email'];
$pass = $_REQUEST['pass'];

$sqlHost = 'localhost';
$sqlUser = 'jsn9';
$sqlDatabase = 'jsn9_ATTHackathon';
$sqlPass = '23899581';

$conn = mysql_connect($sqlHost, $sqlUser, $sqlPass)
   or die("Couldn't connect to MySQL server on $sqlHost: " . mysql_error() . '.');

$db = mysql_select_db($sqlDatabase, $conn)
  or die("Couldn't select database $sqlDatabase: " . mysql_error() . '.');

$sql = "SELECT uid FROM UserLoginTbl WHERE email='" . $email . "' AND pass='" . $pass . "'";

$return = mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');

$record = $record=mysql_fetch_array($return);

if(!(is_null($record) || empty($record)))
{
	echo $record['uid'];
}
else
{
	echo "ERROR NOT FOUND";
}

mysql_close($conn);

?>