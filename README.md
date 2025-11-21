# Taller 6 – Comparación de Algoritmos de Ordenación en Java

> InsertionSort · SelectionSort · BubbleSort · MVC · Métricas · CSV Loader

Este repositorio contiene la implementación completa de tres algoritmos de ordenación in-place integrados dentro de una arquitectura MVC, como parte del Taller 6 de la asignatura Estructura de Datos.
Incluye trazas opcionales, métricas detalladas, generación de datasets y lectura desde archivos CSV.

---

## Estructura del paquete

Se utilizó la estructura solicitada en la guía:

**ed.u2.sorting**

Con subpaquetes organizados de la siguiente manera para mantener cohesión y facilitar la evaluación:

> controller/
> model/
> view/

---

## Implementaciones in-place

Los algoritmos de ordenación modifican directamente el arreglo original, sin emplear arreglos auxiliares.
Esto permite observar realmente los movimientos internos y cumplir con los requisitos del taller.

**Sobrecarga para trazas**

Cada algoritmo incluye la versión:

sort(int[] a, boolean trace)

El parámetro trace permite:

> Mostrar comparaciones
> Mostrar intercambios o desplazamientos
> Imprimir el arreglo en cada iteración
> Visualizar el comportamiento interno del algoritmo

Ideal para fines educativos y para entender el proceso de ordenación paso a paso.

---

## BubbleSort con corte temprano

Se incluyó la optimización:

> if (!swapped) break;

Con ello, el algoritmo termina antes cuando el arreglo ya está ordenado, reduciendo tiempo de ejecución en arreglos casi ordenados.

---

## Controlador: SortingController

El controlador permite:

Seleccionar el algoritmo de ordenación
Ejecutar métricas de comparaciones, swaps y tiempo
Gestionar la vista en consola
Integrar carga de datos desde CSV y datasets generados

---

## Vista: ConsoleView

La vista muestra:

> Resultados ordenados
> Métricas generadas por SortStats
> Información del proceso ejecutado
> Comparación entre algoritmos

---

## Utilidades y Generación de Datos

El proyecto incluye:

> DatasetGenerator

> Permite generar automáticamente listas aleatorias para pruebas.

> CsvDataLoader

> Carga datasets externos desde archivos CSV.

---

## SortingUtils

Funciones de apoyo para el manejo de los arreglos antes y después del proceso.

---

## Cómo Ejecutar el Proyecto

**Compilar**

Desde la raíz del proyecto:

> javac src/main/java/ed/u2/sorting/**/*.java

**Ejecutar**

Ejecutar la clase principal:

> java -cp src/main/java ed.u2.sorting.Main

La ejecución mostrará:

> Selección de algoritmo

> Lectura de datos (CSV o generados)

> Resultados ordenados

> Trazas opcionales

> Métricas completas

---

**Datasets Oficiales / Generados**

El proyecto soporta:

> Carga desde CSV
> Generación automática de datos aleatorios
> Arreglos pequeños para análisis manual
> Arreglos grandes para medición de rendimiento

---

## Casos Borde Considerados

Para validar la robustez del sistema se probaron:

**Arreglo vacío**
**Arreglo con un solo elemento**
**Arreglo ya ordenado**
**Arreglo en orden inverso**
**Arreglo con duplicados**
**Arreglos aleatorios grandes**
**Datos cargados desde CSV**

Cada caso permite verificar la estabilidad de los algoritmos y su comportamiento en el mejor y peor escenario.

## Autores
**Steven Jumbo**
* Github: [@SteveJ4ra](https://github.com/SteveJ4ra)


**Julian Vega**
* GitHub: [@Servio-Julian-Vega-Jimenez](https://github.com/ServioVega)

## Apóyanos

Dale una estrella al repositorio si te gustó el proyecto o te fue útil.
