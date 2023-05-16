# grupo-12-software-mobiles

## Analisis estático y micro optimizaciones

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=je-guerreroa1-uniandes_grupo-12-software-mobile&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=je-guerreroa1-uniandes_grupo-12-software-mobile)

## Disposición de carpetas

El proyecto se encuentra repartido entre las siguientes carpetas:

* **assets**: Recursos a usar en la aplicación de imágenes, principalmente, para construir las vistas del proyecto
* **BuildBundles**: Recopila los archivos *APK* generados de las diferentes versiones
* **Vinylsg12**: Es el proyecto manejado al interior de android studio

## Proceso de instalación y puesta en marcha:

1. Clonar el repositorio: https://github.com/je-guerreroa1-uniandes/grupo-12-software-mobile/tree/develop
2. Importar el proyecto en la carpeta **Vinylsg12** dentro de android studio.
3. Actualizar la configuración JDK y de gradle como lo aconseja android studio después de clonar el repositorio
4. Ejecutar el proyecto en el simulador o dispotivo físico.

## Evidencia de HU

* [(HU01 - Listado albums), (HU02 - Detalle album)](https://github.com/je-guerreroa1-uniandes/grupo-12-software-mobile/blob/develop/BuildBundles/1.0.1/DetalleAlbum.webm)

## Control de cambios

### 1.0.0

* Implementación HU01 - Listado de albums

### 1.0.1

* Implementación HU02 - Detalle de album

### 1.1.0

* Damaged release

### 1.2.0

* Implementación HU03 - Listado de artistas, separados en musicos y bandas
* Implementación HU07 - Crear album
* Adición de navegación de inicio
* Aplciación de theme para aplicación
* Adición de navigation drawer
* Adición de servicio para coleccionistas
* Adición de servicio para albums coleccionados

#### Analisis de aplicación

* Adición de pruebas E2E
* Adición de analisis estático con android lint
* Adición de analisis estático con Sonarcloud
* Adición de Firebase crashlytics para validar estado de salud de la aplicación