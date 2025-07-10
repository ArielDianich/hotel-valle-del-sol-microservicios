berar# Encuentra el PID que usa el puerto 8080
PID=$(lsof -ti :8080)

# Si existe un proceso, lo mata
if [ -n "$PID" ]; then
  echo "Matando proceso en el puerto 8080 (PID: $PID)..."
  kill -9 $PID
  echo "Puerto liberado."
else
  echo "No hay procesos usando el puerto 8080."
fi
