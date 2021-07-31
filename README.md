##Using REPL for development

### To run REPL:
```
clj -A:dev
```

### Main dev command:
- `(go)` to run web server
- `(halt)` to stop web server
- `(reset)` to reset web server
- `(reset-all)` to restart all services

### To run backend REPL:
```
clj -M:dev-backend
```

### To run frontend dev server (shadow-cljs):
```
clj -M:dev-frontend
```
