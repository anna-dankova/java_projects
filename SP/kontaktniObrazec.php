<?php
// Инициализируем переменные для хранения значений полей (чтобы форма их "помнила")
$ime = "";
$email = "";
$sporocilo = "";

// Массив для хранения ошибок
$napake = [];

// Флаг успешной отправки формы
$uspeh = false;

// Проверяем, была ли форма отправлена методом POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    
    // Получаем данные из формы и обрезаем лишние пробелы по краям
    $ime = trim($_POST['ime'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $sporocilo = trim($_POST['sporocilo'] ?? '');

    // --- ВАЛИДАЦИЯ ---

    // 1. Проверка имени (минимум 2 знака)
    if (mb_strlen($ime) < 2) {
        $napake[] = "Ime mora imeti vsaj 2 znaka.";
    }

    // 2. Проверка e-mail (валидный адрес с помощью filter_var)
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $napake[] = "E-poštni naslov ni veljaven.";
    }

    // 3. Проверка сообщения (минимум 10 знаков)
    if (mb_strlen($sporocilo) < 10) {
        $napake[] = "Sporočilo mora imeti vsaj 10 znakov.";
    }

    // --- ПРОВЕРКА РЕЗУЛЬТАТА ---
    // Если массив с ошибками пуст, значит всё в порядке
    if (empty($napake)) {
        $uspeh = true;
    }
}
?>
<!DOCTYPE html>
<html lang="sl">
<head>
    <meta charset="UTF-8">
    <title>Kontaktni obrazec</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .napake { color: red; background: #fee; padding: 10px; border: 1px solid red; border-radius: 4px; }
        .uspeh { color: green; background: #efe; padding: 10px; border: 1px solid green; border-radius: 4px; }
        .polje { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], textarea { width: 300px; padding: 8px; }
        textarea { height: 100px; }
        button { padding: 10px 15px; background: #007BFF; color: white; border: none; cursor: pointer; }
        button:hover { background: #0056b3; }
    </style>
</head>
<body>

    <h2>Kontaktni obrazec</h2>

    <?php
    // --- ВЫВОД РЕЗУЛЬТАТОВ ---
    
    // Если всё прошло успешно, выводим сообщение и НЕ показываем форму
    if ($uspeh) {
        echo "<div class class='uspeh'>";
        // Защищаем вывод через htmlspecialchars
        echo "<p>Hvala, " . htmlspecialchars($ime, ENT_QUOTES, 'UTF-8') . "!</p>";
        echo "<p>Vaš e-poštni naslov: " . htmlspecialchars($email, ENT_QUOTES, 'UTF-8') . "</p>";
        echo "</div>";
    } else {
        // Если есть ошибки, выводим их списком <ul>
        if (!empty($napake)) {
            echo "<div class='napake'><ul>";
            foreach ($napake as $napaka) {
                echo "<li>" . htmlspecialchars($napaka, ENT_QUOTES, 'UTF-8') . "</li>";
            }
            echo "</ul></div><br>";
        }
    ?>

        <!-- ФОРМА (показывается, если форма еще не отправлена или есть ошибки) -->
        <!-- action="" означает, что форма отправляется на эту же страницу -->
        <form action="" method="POST">
            
            <div class="polje">
                <label for="ime">Ime (vsaj 2 znaka):</label>
                <!-- Сохраняем введенное значение через value="..." и защищаем его -->
                <input type="text" id="ime" name="ime" value="<?php echo htmlspecialchars($ime, ENT_QUOTES, 'UTF-8'); ?>">
            </div>

            <div class="polje">
                <label for="email">E-pošta:</label>
                <input type="email" id="email" name="email" value="<?php echo htmlspecialchars($email, ENT_QUOTES, 'UTF-8'); ?>">
            </div>

            <div class="polje">
                <label for="sporocilo">Sporočilo (vsaj 10 znakov):</label>
                <textarea id="sporocilo" name="sporocilo"><?php echo htmlspecialchars($sporocilo, ENT_QUOTES, 'UTF-8'); ?></textarea>
            </div>

            <button type="submit">Pošlji</button>
        </form>

    <?php 
    } // Конец блока else
    ?>

</body>
</html>
