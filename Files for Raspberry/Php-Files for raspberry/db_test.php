<?php
$green ="11";
$blue = "11";
$red = "11";
if (isset($_POST["red"]) && isset($_POST["blue"]) && isset($_POST["green"])) {

    $red = $_POST["red"];
    $blue = $_POST["blue"];
    $green = $_POST["green"];
}

$servername = "localhost";
$username = "phpmyadmin";
$password = "projekti";
$dbname = "phpmyadmin";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "INSERT INTO `LedControlling` (`currenttime`, `Red`, `Green`, `Blue`) VALUES (CURRENT_TIMESTAMP, '$red', '$green', '$blue');";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
while($row = $result->fetch_assoc()) {
        $arr = array( "red" => $row["Red"], "blue" => $row["Blue"], "green"=> $row["Green"]);
        $json_data = json_encode($arr);
        echo $json_data;
}

} else {
    echo "0 results";
}
$conn->close();
?>
