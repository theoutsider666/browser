import scanpy as sc
import pandas as pd

import argparse

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='Parameters to be used for Stacking')
    parser.add_argument('-Newfilename', '--filename', required=True, help='input gene_expression file')
    parser.add_argument('-Name', '--name', required=True, help='input gene_name file')

# -filename
args = parser.parse_args()
filename = args.filename
name = args.name.split(',')  # 列表
# print(name)
species1=args.species1
tissue1=args.tissue1
adatapath='C:\\Users\\MI\\Desktop\\bishe\\数据\\{}\\{}.h5ad'.format(species1,tissue1)
alabelpath='C:\\Users\\MI\\Desktop\\bishe\\数据\\{}\\{}_label.txt'.format(species1,tissue1)
adata=sc.read_h5ad(adatapath)
print("-----------------------------------------------------------")
print(adata)
print("-----------------------------------------------------------")
alabel=pd.read_csv(alabelpath)['x']

# 对数据进标准化操作
sc.pp.normalize_total(adata)
sc.pp.log1p(adata)
# 选择5000个高变基因
sc.pp.highly_variable_genes(adata, n_top_genes=5000)
adata = adata[:, adata.var.highly_variable]
sc.pp.scale(adata)
sc.tl.pca(adata, svd_solver='arpack')
sc.pp.neighbors(adata, n_neighbors=len(alabel.unique()), n_pcs=40)
# sc.tl.louvain(data)
# print(data.var)
sc.tl.umap(adata)

a = []
for i in alabel:
    a.append(i)

adata.obs['celltype'] = a
wpath='C:\\Users\\MI\\Desktop\\bishe\\数据\\{}\\processed\\{}.h5ad'.format(species1,tissue1)
adata.write(wpath)


data = sc.read_h5ad(wpath)
# #细胞聚类可视化
# sc.pl.umap(data,color=['celltype'])
# #gene可视化
# sc.pl.umap(data,color=['CD99'],return_fig=True)
fig = sc.pl.umap(adata, color=name, return_fig=True)
# fig = sc.pl.umap(data,color=['CD99'],return_fig=True)

fig.savefig(f'{filename}')
