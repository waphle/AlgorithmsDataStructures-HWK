# Function to check if the set of parentheses, brackets, and braces is balanced or not
def checkSet(set):
    stack = []

    # Program will check for {, (, and [. Will determine if set is logical or not. Append pushes element
    # to the top of the stack.
    for sym in set:
        if sym in ["{", "(", "["]:
            stack.append(sym)
        else:
            if not stack:
                return False

            top_sym = stack.pop()
            if top_sym == ('{', '(', '['):
                if sym != ("}", ")", "]"):
                    return False
                else:
                    stack.pop()

    if stack:
        return False
    return True

# User input
print('Enter your set of parentheses, brackets, and braces below to see if your set is balanced or not.')
set = input("Enter Here: ")

if __name__ == "__main__":
    if checkSet(set):
        print("Balanced.");
    elif checkSet(set) != ('{', '(', '['):
        print("Please input a valid set. Restart the program.");
    else:
        print("Not Balanced.");
