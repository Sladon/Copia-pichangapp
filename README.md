# PichangApp

Esta aplicación consiste en un juego para Android, donde podrán elegir un héroe o villano y pelear con otros usuarios o el computador.

Durante el semestre veremos clase a clase ejemplos de los contenidos dentro de esta app.

## Clase #6: RecyclerViews

### TODO
Cambiar el FrameLayout por un RecyclerView

Recordar que para crear un RecyclerView, hay que tener en cuenta lo siguiente:
* LayoutManager
* RecyclerView.Adapter
* ViewHolder
  
Para ello deberán seguir los siguientes pasos:
* Agregar un RecyclerView a la vista en cuestión.
* Tener un layout con el item que se quiere agregar a la lista.
* Crear un Adapter que herede de `RecyclerView.Adapter<TeamItemAdapter.ViewHolder>()`.
* Poner en la inner class `ViewHolder` los elementos del item que se quieren personalizar/cambiar/editar/agregar funcionalidades.
* Poner en el Fragment que controla el RecyclerView el LayoutManager y agregar el Adapter al RecyclerView.

