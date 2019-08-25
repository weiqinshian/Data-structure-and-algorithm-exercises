package cn.xiewei.array;

import java.util.Random;

public class Matrix {
    // ����Ԫ��
    private int[][] matrix;
    // �����
    Random random = new Random();

    // Ĭ�Ϲ��췽��
    public Matrix() {
        matrix = new int[3][3];

    }

    // ���������췽����n��
    public Matrix(int n) {
        matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
    }

    // ���������췽����n,m��
    public Matrix(int n, int m) {
        matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
    }

    // ��ȡ����Ԫ�ط���
    public int[][] getMatrix() {
        return matrix;
    }

    // �������������Ԫ��
    public void output() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // ��һ�������ת�þ���
    public Matrix transpose() {

        int n = matrix.length;
        int m = matrix[0].length;

        Matrix transMatrix = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transMatrix.getMatrix()[i][j] = matrix[j][i];
            }
        }

        return transMatrix;
    }

    // �ж�һ�������Ƿ�Ϊ�����Ǿ���
    public boolean isTriangular() {

        // ���෴��˼·�����ж�
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    // �ж��Ƿ�Ϊ�Գƾ���
    public boolean isSymmetry() {

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    // ��������
    public void add(Matrix b) {

        int[][] matrixOfB = b.getMatrix();

        int n = matrixOfB.length;
        int m = matrixOfB[0].length;

        if (n != matrix.length || m != matrix[0].length) {
            System.out.println("����ĳ��Ȳ�һ�£��������");
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] += matrixOfB[i][j];
                }
            }

        }
    }

    public static void main(String[] args) {
        // ����
        Matrix matrix1 = new Matrix(4);

        System.out.println("ԭʼ����");
        matrix1.output();
        Matrix transMatrix = matrix1.transpose();

        System.out.println("ת�þ���");
        transMatrix.output();

        System.out.println("�Ƿ��������Ǿ���");
        System.out.println(matrix1.isTriangular());

        System.out.println("�Ƿ��ǶԳƾ���");
        System.out.println(matrix1.isSymmetry());
        System.out.println("----------------------");
        Matrix matrix2 = new Matrix();
        matrix2.output();
        System.out.println(matrix2.isTriangular());
        System.out.println(matrix2.isSymmetry());
        System.out.println("----------------------");

        Matrix test3 = new Matrix(4);
        Matrix test4 = new Matrix(4);

        test3.add(matrix2);
        System.out.println("����1");
        test3.output();
        System.out.println("����2");
        test4.output();
        System.out.println("�������");
        test3.add(test4);

        test3.output();
    }

}
