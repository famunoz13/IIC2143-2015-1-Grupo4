# IIC2143-2015-1-Grupo4
Proyecto semestral del ramo Ingeniería de Software

La entrega se separa ahora en dos programas (dos proyectos diferentes de eclipse). El primer proyecto es Principal que contiene todo lo de la entrega 1 más las opciones de administrador. El segundo es Cocina que contiene el segundo programa pedido para ser usado en la cocina. Este último se comunica a través de sockets con el primer programa.


#### Configuración proyectos

Es importante notar que es necesario hacer referencia desde el proyecto Cocina al proyecto Principal para que funcionen (usa las mismas clases del modelo). Para hacer esto basta con:

-Desde Cocina

-Ir a las opciones de proyecto/Build Path/Configure Build Path...

-Pestaña proyectos

-Add... y seleccionar Principal


#### Atender cliente

El flujo principal para atender a un cliente queda así:

-Abrir los dos programas

-*Asignar Mesa*

-*Generar Orden* (la orden aparece en el segundo programa)

-*Atender* (desde el segundo programa)

-*Marcar como lista* (el primer programa es avisado)

-Ahora la orden en el primer programa aparece como lista y puede ser entregada

-*Marcar como entregada*

-*Generar Cuenta* (o bien se pueden crear nuevas órdenes)

-*Ingresar pago*


#### Administrar

Un segundo flujo que se implementó es el de menú de administración. Para acceder a este basta con ir a la pestaña *Administrar*. Los datos de acceso son:

-Usuario administrador: admin

-Contraseña: 12345

Si bien vienen escritas por defecto, si no son esos los datos de accesos entonces la autentificación falla.

Por el momento la única opción práctica implementada es *Editar mesas*. Accediendo a esta última se pueden modificar la distribución espacial de las mesas. El modo de usos es: seleccionar una mesa (pinchándola) y luego seleccionar una opción del menú (Ej. *Mover*). Luego de modificar la distribución se puede *Guardar y cargar* para aplicar los cambios o bien *Salir* perdiendo los cambios realizados.