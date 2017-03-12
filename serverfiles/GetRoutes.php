<?php
$sortBy = $_REQUEST['SortBy'];
$sortByAscDesc = $_REQUEST['AscDesc'];
$uid = $_REQUEST['uid'];

$sqlHost = 'localhost';
$sqlUser = 'jsn9';
$sqlDatabase = 'jsn9_ATTHackathon';
$sqlPass = '23899581';

$conn = mysql_connect($sqlHost, $sqlUser, $sqlPass)
   or die("ERROR Couldn't connect to MySQL server on $sqlHost: " . mysql_error() . '.');

$db = mysql_select_db($sqlDatabase, $conn)
   or die("ERROR Couldn't select database $sqlDatabase: " . mysql_error() . '.');

$sql = "";

if($sortBy == 'alph')
{
	if($sortByAscDesc == 'asc')
	{
		$sql = "SELECT * FROM UserRoutesTbl WHERE uid=" . $uid . " ORDER BY StartName ASC";
	}
	else
	{
		$sql = "SELECT * FROM UserRoutesTbl WHERE uid=" . $uid . " ORDER BY StartName DESC";
	}
}
else
{
	if($sortByAscDesc == 'date')
	{
		$sql = "SELECT * FROM UserRoutesTbl WHERE uid=" . $uid . " ORDER BY DateDriven ASC";
	}
	else
	{
		$sql = "SELECT * FROM UserRoutesTbl WHERE uid=" . $uid . " ORDER BY DateDriven DESC";
	}
}

$return = mysql_query($sql,$conn) or die("ERROR Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');

while($record=mysql_fetch_array($return))
{
	echo $record['StartName'] . "\n";
	echo $record['EndName'] . "\n";
	echo $record['DateDriven'] . "\n";
	echo $record['Miles'] . "\n";
	echo $record['TimeSeconds'] . "\n";
	echo $record['TimeMinutes'] . "\n";
	echo $record['Money'] . "\n";
	echo $record['Gas'] . "\n";
	
}

mysql_close($conn);
?>