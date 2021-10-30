## Using REPL for development

### To run backend REPL:
```
clj -M:dev
```

### Main dev command:
- `(go)` to run web server
- `(halt)` to stop web server
- `(reset)` to reset web server
- `(reset-all)` to restart all services


### To run frontend dev server (shadow-cljs):

```
npx shadow-cljs watch app
```
or run alias
```
npm run dev
```

After that you can connect to the REPL using Calva:
`Connect to the running REPL` -> `shadow-cljs` -> `:app`
