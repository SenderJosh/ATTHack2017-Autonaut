<?php
$uid = $_REQUEST['uid'];
$startName = $_REQUEST['startname'];
$endName = $_REQUEST['endname'];

$sqlHost = 'localhost';
$sqlUser = 'jsn9';
$sqlDatabase = 'jsn9_ATTHackathon';
$sqlPass = '23899581';

$conn = mysql_connect($sqlHost, $sqlUser, $sqlPass)
   or die("ERROR Couldn't connect to MySQL server on $sqlHost: " . mysql_error() . '.');

$db = mysql_select_db($sqlDatabase, $conn)
   or die("ERROR Couldn't select database $sqlDatabase: " . mysql_error() . '.');

$sql = "INSERT INTO UserRoutesTbl (
			UID,
			StartName,
			EndName
		)
		VALUES (" . $uid . ",'" . $startName . "','" . $endName . "')";
		
mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');

mysql_close($conn);
?>