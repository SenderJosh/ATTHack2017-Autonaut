<?php
$carorapp = $_REQUEST['carorapp'];
$addTF = $_REQUEST['addtf'];
$uid = $_REQUEST['uid'];
$sname = $_REQUEST['sname'];
$ename = $_REQUEST['ename'];
$finishedIndicator = $_REQUEST['indi'];

$sqlHost = 'localhost';
$sqlUser = 'jsn9';
$sqlDatabase = 'jsn9_ATTHackathon';
$sqlPass = '23899581';

$conn = mysql_connect($sqlHost, $sqlUser, $sqlPass)
   or die("ERROR Couldn't connect to MySQL server on $sqlHost: " . mysql_error() . '.');

$db = mysql_select_db($sqlDatabase, $conn)
   or die("ERROR Couldn't select database $sqlDatabase: " . mysql_error() . '.');

$sql = "";

if($carorapp == "car")
{
	if($addTF == "t")
	{
		//update
		$sql = "UPDATE UserTrackingCurrentRouteTbl SET FinishedAndPostedIndicator=0 WHERE UID=" . $uid;
		mysql_query($sql,$conn);
	}
	else //if here, then that means it's the PI just trying to contact the db looking for the current route
	{
		$sql = "SELECT FinishedAndPostedIndicator FROM UserTrackingCurrentRouteTbl WHERE UID=" . $uid;
		$return = mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');
		$record = $record=mysql_fetch_array($return);

		if(!(is_null($record) || empty($record)))
		{
			echo $record['FinishedAndPostedIndicator'] . "\n";
			echo $sname . "\n";
			echo $ename;
		}
	}
}
else
{
	if($addTF == "t")
	{
		//update
		$sql = "UPDATE UserTrackingCurrentRouteTbl SET StartName='" . $sname . "', EndName='" . $ename . "', FinishedAndPostedIndicator=1 WHERE UID=" . $uid;
		mysql_query($sql,$conn);
		echo $sql;
	}
	else //if here, then that means it's the user in the tracking just waiting.
	{
		$sql = "SELECT FinishedAndPostedIndicator FROM UserTrackingCurrentRouteTbl WHERE UID=" . $uid;
		$return = mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');
		$record = $record=mysql_fetch_array($return);

		if(!(is_null($record) || empty($record)))
		{
			echo $record['FinishedAndPostedIndicator'];
		}
	}
}
mysql_close($conn);
?>