# R 语言之 if

```R
medium <- "LinkedIn"
num_views <- 14

if (medium == "LinkedIn") {
  print("Showing LinkedIn information")
}
#"Showing LinkedIn information"

if (num_views > 15) {
  print("You are popular!")
}
```

```R
medium <- "LinkedIn"
num_views <- 14

if (medium == "LinkedIn") {
  print("Showing LinkedIn information")
} else {
  print("Unknown medium")
}
# "Showing LinkedIn information"

if (num_views > 15) {
  print("You're popular!")
} else {
  print("Try to be more visible!")
}
# "Try to be more visible!"
```

```R
medium <- "LinkedIn"
num_views <- 14

if (medium == "LinkedIn") {
  print("Showing LinkedIn information")
} else if (medium == "Facebook") {
  print("Showing Facebook information")
} else {
  print("Unknown medium")
}
# "Showing LinkedIn information"

if (num_views > 15) {
  print("You're popular!")
} else if (num_views <= 15 & num_views > 10) {
  print("Your number of views is average")
} else {
  print("Try to be more visible!")
}
# "Your number of views is average"
```

```R
li <- 15
fb <- 9

if (li >= 15 & fb >= 15) {
  sms <- 2 * (li + fb)
} else if (li < 10 & fb < 10) {
  sms <- 0.5 * (li + fb)
} else {
  sms <- li + fb
}

sms
# 24
```

