 * StateMachine -> { Statement }
 * Statement    -> Events | ResetEvents | Commands | State
 * Events       -> "events" "{" { NAME CHAR } "}"
 * Commands     -> "commands" "{" { NAME CHAR } "}"
 * ResetEvents  -> "resetEvents" "{" { NAME } "}"
 * State        -> "state" "{" [Actions] { Transition } "}"
 * Actions      -> "actions" "{" { NAME } "}"
 * Transition   -> NAME "->" NAME