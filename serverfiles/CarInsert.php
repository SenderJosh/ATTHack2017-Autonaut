<?php
$uid = $_REQUEST['uid'];
$startName = $_REQUEST['startname'];
$endName = $_REQUEST['endname'];
$dateDriven = $_REQUEST['date'];
$miles = $_REQUEST['miles'];
$timeSeconds = $_REQUEST['timeseconds'];
$timeMinutes = $_REQUEST['timeminutes'];
$money = $_REQUEST['money'];
$gas = $_REQUEST['gas'];

$sqlHost = 'localhost';
$sqlUser = 'jsn9';
$sqlDatabase = 'jsn9_ATTHackathon';
$sqlPass = '23899581';

$conn = mysql_connect($sqlHost, $sqlUser, $sqlPass)
   or die("ERROR Couldn't connect to MySQL server on $sqlHost: " . mysql_error() . '.');

$db = mysql_select_db($sqlDatabase, $conn)
   or die("ERROR Couldn't select database $sqlDatabase: " . mysql_error() . '.');

$sql = "SELECT 	AVG(Money + $money) as Money, 
				AVG(Gas + $gas) as Gas, 
				AVG(TimeMinutes + $timeMinutes) as TimeMinutes, 
				AVG(TimeSeconds + $timeSeconds) as TimeSeconds, 
				AVG(Miles + $miles) as Miles
				FROM UserRoutesAuditTbl WHERE UID=" . $uid . " AND StartName='" . $startName . "' AND EndName='" . $endName . "' ";

$return = mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');

$record = $record=mysql_fetch_array($return);
if(!(is_null($record) || empty($record)))
{ 
	print_r(array_values($record));
	
	//Get averages. These averages get UPDATED into the UserRoutesTbl, and our original values above get put into the audit table
	$moneyCurr = $record['Money'];
	$gasCurr = $record['Gas'];
	$milesCurr = $record['Miles'];
	$timeMinutesCurr = $record['TimeMinutes'];
	$timeSecondsCurr = $record['TimeSeconds'];

	
	$sql = "UPDATE UserRoutesTbl 
			SET Money=" . $moneyCurr . ",
			TimeMinutes=" . $timeMinutesCurr . ",
			TimeSeconds=" . $timeSecondsCurr . ",
			Miles=" . $milesCurr . ",
			Gas=" . $gasCurr . ",
			DateDriven='" . date("Y-m-d H:i:s") . "'
			 WHERE UID=" . $uid . " AND StartName='" . $startName . "' AND EndName='" . $endName . "' ";
	mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');
	
	//now put the shit in audit
	$sql = "INSERT INTO UserRoutesAuditTbl (
				UID,
				StartName,
				EndName,
				DateDriven,
				Miles,
				TimeSeconds,
				TimeMinutes,
				Money,
				Gas)
				VALUES (" . $uid . ",'" . $startName . "','" . $endName . "','" . date("Y-m-d H:i:s") . "'," . $miles . "," . $timeSeconds ."," . 
				$timeMinutes . "," . $money . "," . $gas . ")";
	
	mysql_query($sql,$conn) or die("Couldn't perform query $sql (".__LINE__."): " . mysql_error() . '.');

}

mysql_close($conn);
?>