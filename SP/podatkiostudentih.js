const studenti = [
  { ime: "Rok", ocena: 4 },
  { ime: "Maja", ocena: 9 },
  { ime: "Jan", ocena: 5 },
  { ime: "Ana", ocena: 8 },
  { ime: "Luka", ocena: 6 }
];


console.log("Študenti z oceno večjo od 5:");
studenti.forEach(student => {
  if (student.ocena > 5) {
    console.log(student.ime);
  }
});

let vsotaOcen = 0;
studenti.forEach(student => {
  vsotaOcen += student.ocena;
});
const povprecnaOcena = vsotaOcen / studenti.length;
console.log("Povprečna ocena:", povprecnaOcena);

console.log("Seznam študentov in ocen:");
studenti.forEach(student => {
  console.log(`${student.ime}: ${student.ocena}`);
});
