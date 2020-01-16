import sys

def main():
    placeholders="w.11, s.11, w.12, s.12, w.21, s.21, w.22, s.22, w.31, s.31, w.32, s.32, w.41, s.41, w.42, s.42, w.51, s.51, w.52, s.52, w.61, s.61, w.62, s.62, w.71, s.71, w.72, s.72, w.81, s.81, w.82, s.82, a.w0, w.a1, w.a2, w.a3, w.a4, w.a5, w.a6, w.a7, w.a8, b.w0, w.b1, w.b2, w.b3, w.b4, w.b5, w.b6, w.b7, w.b8, c.w0, w.c1, w.c2, w.c3, w.c4, w.c5, w.c6, w.c7, w.c8".split(", ")

    values = sys.argv[1].split(", ")

    with open("neural-network-template.svg", "rt") as f_in:
        with open("neural-network-written.svg", "wt") as f_out:
            for line in f_in:
                for p, v in zip(placeholders, values):
                    line = line.replace(p, v)
                f_out.write(line)

if __name__ == '__main__':
    main()

