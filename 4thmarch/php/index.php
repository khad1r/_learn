<?php
session_start();
if(!empty($_SESSION['data'])){
    $data=$_SESSION['data'];
}else{
    $data = [];
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Array</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="container">
        <button class="btn add" id="add">+ Tambah</button>
        <form method="GET" name="cari" class="search">
            <input type="text" class="input link" name="cari" value="" placeholder="Cari">
            <button type="submit" class="btn">&#128269;</button>
            <?php
                if(isset($_GET['cari'])){
                    $cari = $_GET['cari'];
                    if(empty($cari)){
                        header('location: index.php');
                    }
                    $cari_data = array_cari($data,$cari);
                    $list = $cari_data;
                    ?><a href="index.php">back</a><?php
                }else{
                    $list = array_keys($data);
                }
            ?>
        </form>
        <table>
            <thead>
                <tr>
                    <td>No</td>
                    <td>Nama</td>
                    <td>Jurusan</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>
                <?php
                    $i = 1;
                    foreach($list as $index){
                        ?>
                        <tr>
                            <td><?php echo $i++?></td>
                            <td><?php echo $data[$index][0]?></td>
                            <td><?php echo $data[$index][1]?></td>
                            <td>
                                <a href="?edit=<?php echo $index ?>" class="btn">&#128393; Edit</a>
                                <a href="?hapus=<?php echo $index ?>" onclick="return confirm('Data akan dihapus')" class="btn">&#128465; Hapus</a>
                            </td>                                                                                                        
                        </tr>
                        <?php
                    }
                ?>
            </tbody>
        </table>
        <div class="modal" id="modal">
            <div class="modal-content" align="center">
                <form method="POST" name="add">
                    <input class="input" type="text" value="" name="add_nama" placeholder="Nama"><br>
                    <input class="input" type="text" value="" name="add_jur" placeholder="Jurusan"><br>
                    <input class="btn" type="submit" name="add" value="Simpan">
                    <a class="btn" id="batal">Batal</a>
                </form>
            </div>
        </div>
        <?php
        if(isset($_GET['edit'])){
            $id=$_GET['edit'];
            ?>
            <div class="modal" id="modal" style="display: block;">
                <div class="modal-content" align="center">
                    <form method="POST" name="edit_simpan">
                        <input class="input" type="text" value="<?=$data[$id][0];?>" name="add_nama" placeholder="Nama"><br>
                        <input class="input" type="text" value="<?=$data[$id][1];?>" name="add_jur" placeholder="Jurusan"><br>
                        <input class="btn" type="submit" name="edit_simpan" value="Simpan">
                        <a href="index.php" class="btn" id="batal">Batal</a>
                    </form>
                </div>
            </div>
            <?php
        }
        ?>
    </div>
    <script src="app.js"></script>
</body>

</html>
<?php
if (isset($_POST['add'])){
    if(!empty($_POST['add_nama']&&$_POST['add_jur'])){
        $tambah = array($_POST['add_nama'],$_POST['add_jur']);
        $_SESSION['data'][]=$tambah;
        header('location: index.php');
    }else{
        ?>
        <script>alert('Data Tidak Lengkap');</script>
        <?php
    }
}

if (isset($_POST['edit_simpan'])){
    if(!empty($_POST['add_nama']&&$_POST['add_jur'])){
        $edit = array($_POST['add_nama'],$_POST['add_jur']);
        $_SESSION['data'][$id] = $edit;
        header('location: index.php');
    }else{
        ?>
        <script>alert('Data Tidak Lengkap');</script>
        <?php
    }
}

if(isset($_GET['hapus'])){
    $id=$_GET['hapus'];
    unset($data[$id]);
    $_SESSION['data'] = array_values($data);
    header('location: index.php');
}

function array_cari($data, $keyword) {
    $index = array();
    foreach($data as $v => $search) {
        if (strpos($search[0], $keyword) !== FALSE || strpos($search[1], $keyword) !== FALSE )
            $index[]=$v;
    }
    return $index;

}